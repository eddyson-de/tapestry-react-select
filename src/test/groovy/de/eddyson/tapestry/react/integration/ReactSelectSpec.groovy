package de.eddyson.tapestry.react.integration
import de.eddyson.tapestry.react.integration.pages.ReactSelectAsyncDemo
import de.eddyson.tapestry.react.integration.pages.ReactSelectDemo;
import de.eddyson.tapestrygeb.JettyGebSpec


class ReactSelectSpec extends JettyGebSpec {

  def "Simple loading test"(){
    given:
    to ReactSelectDemo
    expect:
    selectValue.displayed
    selectValue.text() == "One"
    when:
    selectValue.click()
    then:
    selectMenu.displayed
  }

  def "Select.Async test"(){
    given:
    to ReactSelectAsyncDemo
    expect:
    selectControl.displayed
    when:
    selectControl.click()
    then:
    selectMenu.displayed
    when:
    selectInput << 'one'
    then:
    waitFor {
      selectOption("One").displayed
    }
    when:
    selectOption("One").click()
    then:
    waitFor {
      selectValue.text() == "One"
    }
    
  }
}

