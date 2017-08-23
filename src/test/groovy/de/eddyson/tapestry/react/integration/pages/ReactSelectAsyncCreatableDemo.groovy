package de.eddyson.tapestry.react.integration.pages

import de.eddyson.tapestrygeb.TapestryPage

class ReactSelectAsyncCreatableDemo extends TapestryPage {

  static url = "reactselectasynccreatabledemo"

  static at = { title == "React Select AsyncCreatable Demo" }

  static content = {
    selectControl { $('.Select-control') }
    selectInput { $('.Select-input input') }
    selectOption  { value-> $('.Select-option', text: contains(value)) }
    selectValue { $('.Select-value') }
    selectMenu  { $('.Select-menu') }
  }
}

