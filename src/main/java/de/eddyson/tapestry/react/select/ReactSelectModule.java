package de.eddyson.tapestry.react.select;

import java.io.IOException;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.annotations.Path;
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
      @Path("classpath:de/eddyson/tapestry/react/select/react-select.js") final Resource reactSelect,
      @Path("classpath:de/eddyson/tapestry/react/select/react-select.min.js") final Resource reactSelectMin,
      @Path("classpath:de/eddyson/tapestry/react/select/react-input-autosize.js") final Resource reactInputautoSize,
      @Path("classpath:de/eddyson/tapestry/react/select/react-input-autosize.min.js") final Resource reactInputautoSizeMin,
      @Path("classpath:de/eddyson/tapestry/react/select/classnames.js") final Resource classnames,
      @Symbol(ReactSelectSymbols.CONTRIBUTE_CLASSNAMES_MODULE) final boolean contributeClassnamesModule,
      @Symbol(ReactSelectSymbols.CONTRIBUTE_REACT_INPUT_AUTOSIZE_MODULE) final boolean contributeInputAutosizeModule,
      @Symbol(SymbolConstants.PRODUCTION_MODE) final boolean productionMode) throws IOException {
    if (contributeClassnamesModule) {
      configuration.add("classnames", new JavaScriptModuleConfiguration(classnames));
    }
    if (contributeInputAutosizeModule) {
      configuration.add("react-input-autosize",
          new JavaScriptModuleConfiguration(productionMode ? reactInputautoSizeMin : reactInputautoSize));
    }
    configuration.add("react-select", new JavaScriptModuleConfiguration(productionMode ? reactSelectMin : reactSelect));
  }

  @Contribute(SymbolProvider.class)
  @FactoryDefaults
  public static void configureSymbolDefaults(final MappedConfiguration<String, Object> configuration) {
    configuration.add(ReactSelectSymbols.CONTRIBUTE_CLASSNAMES_MODULE, true);
    configuration.add(ReactSelectSymbols.CONTRIBUTE_REACT_INPUT_AUTOSIZE_MODULE, true);

  }

}
