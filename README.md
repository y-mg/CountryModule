# CountryModule
<img width="250px" height="500px" src="/sample/ko.gif" /> <img width="250px" height="500px" src="/sample/en.gif" />
<br/>
<br/>



## 1. CountryPicker

### Kotlin

| Option | Parameter | Description(KO) | Description(EN) |
|:----------|:----------|:----------|:----------|
| setLocale | KO or EN | 언어 설정 | Language Setting |
| getLoadCountries(<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;context: Context<br/>) | Context | 국가 정보 리스트 가져오기 | Get list of country information |
| getCountryFlag(<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;nameCode: String<br/>) | Country Name Code<br/>(KR, US...) | 국기 가져오기 | Get country flag |
| getDetectedCountry(<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;context: Context<br/>) | Context | 현재 국가 정보 가져오기 | Get current country information |
<br/>
<br/>



## 2. Initialer

### Kotlin

| Option | Parameter | Description(KO) | Description(EN) |
|:----------|:----------|:----------|:----------|
| setInitial(<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;textInitial: TextView,<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;items: List<CountryPickerModel>,<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;position: Int<br/>) | Initial TextView,<br/>Country List,<br/>List Item Position | 이니셜 설정 | Initial Setting(A, B...) |
| setInitial(<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;textInitial: TextView,<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;editSearch: EditText,<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;items: List<CountryPickerModel>,<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;position: Int<br/>) | Initial TextView,<br/>Search EditText,<br/>Country List,<br/>List Item Position | 이니셜 설정<br/>(검색용) | Initial Setting(A, B...)<br/>(Use Searching) |
<br/>
<br/>


