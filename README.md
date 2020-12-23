# CountryModule
<img width="250px" height="500px" src="/sample/ko.gif" /> <img width="250px" height="500px" src="/sample/en.gif" />
<br/>
<br/>



## Install
[![](https://jitpack.io/v/y-mg/countrymodule.svg)](https://jitpack.io/#y-mg/countrymodule)

Add Jitpack to your repositories in your `build.gradle` file

```groovy
allprojects {
    repositories {
      // ...
      maven { url 'https://jitpack.io' }
    }
}
```

Add the below to your dependencies, again in your gradle.build file

```groovy
implementation 'com.github.y-mg:countrymodule:{version}'
```
<br/>
<br/>



## 1. CountryPicker

> 이것은 국가 정보를 가져오는 Object 클래스입니다.<br/>
> This is the Object Class from which the country information is obtained.


### Kotlin Function

```kotlin
/**
 * - 지역(언어)를 설정한다.
 * - Set up a locale(language).
 *
 * @param locale -> KO(korean) or EN(English)
 */
fun setLocale(locale: String)

/**
 * - 현재 국가 정보를 찾는다.
 * - Detect current national information.
 *
 * @param context
 */
fun getDetectedCountry(context: Context)

/**
 * - 국기를 가져온다.
 * - Getting the country flag.
 *
 * @param countryNameCode -> Country Name Code(KR, US...)
 */
fun getCountryFlag(countryNameCode: String)

/**
 * - 국가 리스트를 가져온다.
 * - Getting in a list of countries.
 *
 * @param context
 */
fun getLoadCountries(context: Context)
```
<br/>
<br/>



## 2. Initialer

> 이것은 Initial 을 설정할 수 있는 Object 클래스입니다.<br/>
> This is an Object Class that allows you to set Initial.


### Kotlin Function

```kotlin
/**
 * - 검색 기능이 없는 경우 Initial 을 설정한다.(ㄱ...ㅎ, A...Z)
 * - If there is no search function, set the initials.(ㄱ...ㅎ, A...Z)
 *
 * @param textInitial -> This is TextView to bind your initials
 *
 * @param items -> Country List
 *
 * @param position -> Position of country list items
 */
fun setInitial(
    textInitial: AppCompatTextView,
    items: List<CountryPickerModel>,
    position: Int
)

/**
 * - 검색 기능이 있는 경우 Initial 을 설정한다.(ㄱ...ㅎ, A...Z)
 * - If there is search function, set the initials.(ㄱ...ㅎ, A...Z)
 *
 * @param textInitial -> This is TextView to bind your initials.
 *
 * @param editSearch -> This is a search word
 *
 * @param items -> Country List
 *
 * @param position -> Position of country list items
 */
fun setInitial(
    textInitial: AppCompatTextView,
    editSearch: String,
    items: List<CountryPickerModel>,
    position: Int
)
```
<br/>
<br/>


