package de.eddyson.testapp.modules;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.ImportModule;
import org.apache.tapestry5.ioc.services.ApplicationDefaults;
import org.apache.tapestry5.ioc.services.SymbolProvider;

import de.eddyson.tapestry.react.select.ReactSelectModule;

@ImportModule({ ReactSelectModule.class })
public final class TestModule {

  @Contribute(SymbolProvider.class)
  @ApplicationDefaults
  public static void configureApplicationDefaults(final MappedConfiguration<String, Object> configuration) {
    configuration.add(SymbolConstants.JAVASCRIPT_INFRASTRUCTURE_PROVIDER, "jquery");
  }

  private TestModule() {
  }

}
