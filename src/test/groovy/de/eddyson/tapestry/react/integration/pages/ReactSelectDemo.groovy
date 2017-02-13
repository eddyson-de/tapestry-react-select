package de.eddyson.tapestry.react.integration.pages

import de.eddyson.tapestrygeb.TapestryPage

class ReactSelectDemo extends TapestryPage {

  static url = "reactselectdemo"

  static at = { title == "React Select Demo" }

  static content = {
    selectValue { $('.Select-value') }
    selectMenu  { $('.Select-menu') }
  }
}
