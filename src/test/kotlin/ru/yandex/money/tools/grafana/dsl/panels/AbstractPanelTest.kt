package ru.yandex.money.tools.grafana.dsl.panels

import org.testng.annotations.AfterMethod

abstract class AbstractPanelTest {

    @AfterMethod
    fun afterMethod() {
        idGenerator = 1
        x = 0
        y = 0
    }
}
