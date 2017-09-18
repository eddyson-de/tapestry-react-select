# tapestry-react-select [![Build Status](https://travis-ci.org/eddyson-de/tapestry-react-select.svg?branch=master)](https://travis-ci.org/eddyson-de/tapestry-react-select)
# Notice
This library is obsolete. It was originally created because `react-select` did not provide a proper AMD module. This has been addressed in `react-select v1.0.0-rc.6`, so the regular distribution can now be used with Tapestry.

If you're using [tapestry-webjars](https://github.com/eddyson-de/tapestry-webjars), you can set things up like this:

### `build.gradle`:
```groovy
dependencies {
  runtime 'org.webjars.npm:react-select:1.0.0-rc.10'
}
```
### `AppModule.java`:
```java
@Contribute(ModuleManager.class)
public static void setupJSModules(final MappedConfiguration<String, JavaScriptModuleConfiguration> configuration,
  @Path("webjars:react-select:$version/dist/react-select.js") final Resource reactSelect,
  @Path("webjars:react-select:$version/dist/react-select.min.js") final Resource reactSelectMin,
  @Path("webjars:react-input-autosize:$version/dist/react-input-autosize.js") final Resource reactInputautoSize,
  @Path("webjars:react-input-autosize:$version/dist/react-input-autosize.min.js") final Resource reactInputautoSizeMin,
  @Path("webjars:classnames:$version/index.js") final Resource classnames,
  @Symbol(SymbolConstants.PRODUCTION_MODE) final boolean productionMode) {
  configuration.add("classnames", new JavaScriptModuleConfiguration(classnames));
  configuration.add("react-input-autosize",
        new JavaScriptModuleConfiguration(productionMode ? reactInputautoSizeMin : reactInputautoSize));
  configuration.add("react-select", new JavaScriptModuleConfiguration(productionMode ? reactSelectMin : reactSelect));
}
```
See https://github.com/eddyson-de/tapestry-react for integration of React and Tapestry.
