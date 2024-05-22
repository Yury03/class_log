package com.example.data.database.dao

import com.example.data.database.models.*

interface DatabaseDao {
    interface AttendancesDao {
        suspend fun allAttendances(): List<Attendance>
    }

    interface LessonsDao {
        suspend fun allLessons(): List<Lesson>
        suspend fun getLessonsByScheduleId(scheduleId: Int): List<Lesson>
//        suspend fun lesson(id: Int): Lesson?
//        suspend fun getLessonsByIdList(idList: List<Int>): List<Lesson>
//        suspend fun addNewLesson(title: String, body: String): Lesson?
//        suspend fun editLesson(id: Int, title: String, body: String): Boolean
//        suspend fun deleteLesson(id: Int): Boolean
    }

    interface NotificationsDao {
        suspend fun allNotifications(): List<Notification>
    }

    interface SchedulesDao {
        suspend fun allSchedule(): List<Schedule>
        suspend fun getWeekScheduleByUserId(userId: Int): List<Schedule>
    }

    interface SchoolClassesDao {
        suspend fun allSchoolClasses(): List<SchoolClass>
    }

    interface StudentsDao {
        suspend fun allStudents(): List<Student>
    }

    interface SubjectsDao {
        suspend fun allSubjects(): List<Subject>
    }

    interface UsersDao {
        suspend fun allUsers(): List<User>
        suspend fun addNewUser(email: String, fullName: String, password: String, isClassTeacher: Boolean): User?
        suspend fun editPassword(id: Int, newPassword: String): Boolean
        suspend fun getUserIdWithAuthentication(email: String, inputPassword: String): Int?
    }

    interface KeysDao {
        suspend fun getKeyByUserId(userId: Int): Key?
        suspend fun deleteKey(userId: Int): Boolean
        suspend fun getUserIdByKeyString(key: String): Int?
    }

}