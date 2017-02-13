package de.eddyson.tapestry.react.select;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.function.Function;

import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.internal.util.VirtualResource;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.services.FactoryDefaults;
import org.apache.tapestry5.ioc.services.SymbolProvider;
import org.apache.tapestry5.services.javascript.JavaScriptModuleConfiguration;
import org.apache.tapestry5.services.javascript.ModuleManager;

//https://github.com/JedWatson/react-select/issues/270
public class ReactSelectModule {

  @Contribute(ModuleManager.class)
  public static void setupJSModules(final MappedConfiguration<String, JavaScriptModuleConfiguration> configuration,
      @Path("webjars:react-select:$version/dist/react-select.js") final Resource reactSelect,
      @Path("webjars:react-input-autosize:$version/dist/react-input-autosize.js") final Resource reactInputautoSize,
      @Path("webjars:classnames:$version/index.js") final Resource classnames,
      @Symbol(ReactSelectSymbols.CONTRIBUTE_CLASSNAMES_MODULE) final boolean contributeClassnamesModule,
      @Symbol(ReactSelectSymbols.CONTRIBUTE_REACT_INPUT_AUTOSIZE_MODULE) final boolean contributeInputAutosizeModule)
      throws IOException {
    if (contributeClassnamesModule) {
      configuration.add("classnames", new JavaScriptModuleConfiguration(classnames));
    }
    if (contributeInputAutosizeModule) {
      configuration.add("react-input-autosize", new JavaScriptModuleConfiguration(rewrite(reactInputautoSize,
          content -> content.replace("window['React']", "require('react')").replace("define([]", "define(['react']"))));
    }
    configuration.add("react-select",
        new JavaScriptModuleConfiguration(rewrite(reactSelect,
            content -> content.replace("window['React']", "require('react')")
                .replace("window['React']", "require('react')").replace("window['ReactDOM']", "require('react-dom')")
                .replace("window['classNames']", "require('classnames')")
                .replace("window['AutosizeInput']", "require('react-input-autosize')")
                .replace("define([]", "define(['react', 'react-dom', 'classnames', 'react-input-autosize']"))));
  }

  @Contribute(SymbolProvider.class)
  @FactoryDefaults
  public static void configureSymbolDefaults(final MappedConfiguration<String, Object> configuration) {
    configuration.add(ReactSelectSymbols.CONTRIBUTE_CLASSNAMES_MODULE, true);
    configuration.add(ReactSelectSymbols.CONTRIBUTE_REACT_INPUT_AUTOSIZE_MODULE, true);

  }

  private static Resource rewrite(final Resource original, final Function<String, String> transformer)
      throws IOException {
    try (InputStream is = original.openStream(); Scanner sc = new Scanner(is, "UTF-8")) {
      sc.useDelimiter("\\A");
      String content = sc.next();
      String transformedContent = transformer.apply(content);
      final byte[] bytes = transformedContent.getBytes(StandardCharsets.UTF_8);
      return new RewrittenResource(bytes, "generated-module-for-" + original.getFile(),
          "Rewritten resource for " + original.toString());
    }
  }

  private static final class RewrittenResource extends VirtualResource {

    private final byte[] bytes;
    private final String fileName;
    private final String description;

    public RewrittenResource(final byte[] bytes, final String fileName, final String description) {
      this.bytes = bytes;
      this.fileName = fileName;
      this.description = description;
    }

    @Override
    public InputStream openStream() throws IOException {
      return new ByteArrayInputStream(bytes);
    }

    @Override
    public String getFile() {
      return fileName;
    }

    @Override
    public URL toURL() {
      return null;
    }

    @Override
    public String toString() {
      return description;
    }
  }

}
