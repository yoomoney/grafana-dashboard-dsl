package ru.yandex.money.tools.grafana.dsl.panels

import org.amshove.kluent.shouldBe
import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.jsonFile
import ru.yandex.money.tools.grafana.dsl.shouldEqualToJson

class TextPanelBuilderTest : AbstractPanelTest() {

    @Test
    fun `should create empty text panel with MD mode `() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.textPanel(title = "Empty Test Panel") {}

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("text/EmptyTextPanel.json")
    }

    @Test
    fun `should create text panel with MD content`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.textPanel(title = "Test Panel MD") {
            content = "# Text \r\n* **panel**"
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("text/MDTextPanel.json")
    }

    @Test
    fun `should create text panel with HTML content`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.textPanel(title = "Test Panel HTML") {
            content = "<span>Hello</span>"
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("text/HTMLTextPanel.json")
    }

    @Test
    fun `should create transparent text panel `() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.textPanel(title = "Transparent Panel") {
            transparent = true
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("text/TransparentTextPanel.json")
    }

    @Test
    fun `should create text panel with empty title`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.textPanel() {}

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("text/TextPanelWithEmptyTitle.json")
    }
}
