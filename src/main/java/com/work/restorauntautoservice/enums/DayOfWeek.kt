package com.work.restorauntautoservice.enums

enum class DayOfWeek(val percentage: Int) {
    MONDAY(Percentage.TEN),
    TUESDAY(Percentage.TEN),
    WEDNESDAY(Percentage.FIFTEEN),
    THURSDAY(Percentage.TWENTY),
    FRIDAY(Percentage.TWENTY),
    SATURDAY(Percentage.TWENTY),
    SUNDAY(Percentage.FIFTEEN)
}