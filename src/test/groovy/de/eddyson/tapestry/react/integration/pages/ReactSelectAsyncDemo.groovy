package de.eddyson.tapestry.react.integration.pages

import de.eddyson.tapestrygeb.TapestryPage

class ReactSelectAsyncDemo extends TapestryPage {

  static url = "reactselectasyncdemo"

  static at = { title == "React Select Async Demo" }

  static content = {
    selectControl { $('.Select-control') }
    selectInput { $('.Select-input input') }
    selectOption  { value-> $('.Select-option', text: contains(value)) }
    selectValue { $('.Select-value') }
    selectMenu  { $('.Select-menu') }
  }
}

