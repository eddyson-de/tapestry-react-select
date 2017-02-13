package de.eddyson.tapestry.react.integration
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
}
