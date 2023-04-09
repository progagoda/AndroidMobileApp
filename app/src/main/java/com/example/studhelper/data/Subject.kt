package com.example.studhelper.data
//TODO добавить поле лист записавшихся студентов
//TODO изменить тип пол group на объект
data class Subject(
    val name: String,
    val group: String
)
private val names = listOf(
    "Методы и средства программной инженерии",
    "Веб-программирование",
    "Информационные системы и базы данных",
    "Проектирование пользовательских интерфейсов",
    "Архитектура компьютера",
    "Бизнес процессы программных систем",
    "Тестирование программного обеспечения"
)
private val groups = listOf(
    "P33131"
)
private fun subjects(): List<Subject>{
    val subjects = mutableListOf<Subject>()
    for ((index,subject) in names.withIndex()){
        subjects.add(Subject(subject,"P33131"))
    }
    return subjects
}
object SubjectsRepo{
    fun getSubjects(): List<Subject> = subjects()
}
fun generateRandomSubject(): Subject {
    return subjects().random()
}