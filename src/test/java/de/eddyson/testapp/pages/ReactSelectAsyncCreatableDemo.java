package de.eddyson.testapp.pages;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.PublishEvent;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;

import de.eddyson.tapestry.react.select.ReactSelectConstants;

@Import(module = { "testapp/SelectAsyncCreatableTest" }, stylesheet = ReactSelectConstants.REACT_SELECT_STYLESHEET)
public class ReactSelectAsyncCreatableDemo {

  @PublishEvent
  @OnEvent("providevalues")
  Object provideValues() {
    return new JSONObject("values", new JSONArray(new JSONObject("value", "one", "label", "One"),
        new JSONObject("value", "two", "label", "Two"), new JSONObject("value", "three", "label", "Three")));
  }

}
