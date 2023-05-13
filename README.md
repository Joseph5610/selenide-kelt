Extension functions to make Selenide tests with Kotlin awesome!

[![license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

## Maven dependency:

Gradle:
```groovy
repositories {
    maven {
        url "https://jitpack.io" 
    }
}

dependencies {
    testImplementation "org.strangeway:selenide-kelt:1.1.1"
}
```

## Examples:

1. Get element by any of standard By (with named arguments instead of By):
    ```kotlin
    open("/login")
   
    // search by CSS as simple as possible:
    elt("#submit").click()
   
    // or you may specify selector explicitly:    
    elt(css = "#submit").click()
    elt(cssClass = "message").shouldHave(text("Hello"))
    
    elt(text = "Sign in").click()
    elt(name = "password").setValue("qwerty").pressEnter()
    
    elt(xpath = "//*[@id='search-results']//a[contains(text(),'selenide.org')]").click()
    ```

2. Or many elements:
    ```kotlin
    elts(id = "search-results").shouldBe(visible)
    elts("#search-results a").elt(text = "selenide.org").click()
    ```

3. Element inside of elements:
   ```kotlin
   elt(tag = "header").elt(id = "menu").click()
   elt(tag = "header").elts(cssClass = "item").shouldHave(size(5))
   ```

4. Build PageObject classes easier:
    ```kotlin
    import org.strangeway.kelt.*

    class GoogleResultsPage {
        val results = elt(id = "results")
    }
    
    class GoogleSearchPage {
        private val searchBox = elt(name = "q")
    
        fun search(query: String): GoogleResultsPage {
            searchBox.setValue(query).pressEnter()
            return page() // no need to duplicate <GoogleResultsPage> type
        }
    }
   
    // ...
    openPage<GoogleSearchPage>("http://google.com").search("Selenide!")
   
    // get page on demand without opening
    val googlePage = page<GoogleResultsPage>()
    ```
   
5. Check conditions with infix functions:
    ```kotlin
    import org.strangeway.kelt.*
    import com.codeborne.selenide.Condition.*
   
    elt(tag = "header") shouldBe visible
    elt(css = ".footer") shouldHave text("text")
   
    elts(".all") shouldBe CollectionCondition.empty
    elts(".parts") shouldHave CollectionCondition.texts("1", "2")
    ```