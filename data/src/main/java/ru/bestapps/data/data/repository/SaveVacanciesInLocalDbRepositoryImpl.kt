package ru.bestapps.data.data.repository

import ru.bestapps.data.data.storage.models.Address
import ru.bestapps.data.data.storage.models.Experience
import ru.bestapps.data.data.storage.models.Salary
import ru.bestapps.data.data.storage.room.dao.SaveVacanciesDao
import ru.bestapps.data.data.storage.room.entity.Vacancy
import ru.bestapps.domain.models.AddressDomain
import ru.bestapps.domain.models.ExperienceDomain
import ru.bestapps.domain.models.SalaryDomain
import ru.bestapps.domain.models.VacancyDomain
import ru.bestapps.domain.repository.SaveVacanciesInLocalDbRepository

class SaveVacanciesInLocalDbRepositoryImpl(private val saveVacanciesDao: SaveVacanciesDao): SaveVacanciesInLocalDbRepository {
    override suspend fun saveVacancies(vacancies: List<VacancyDomain>) {
        for (vacancy in vacancies) {
            saveVacanciesDao.insertVacancy(helperToRoom(vacancy))
        }
    }
    private fun helperToRoom(vacancyDomain: VacancyDomain): Vacancy {
        return Vacancy(
            id = vacancyDomain.id,
            lookingNumber = vacancyDomain.lookingNumber,
            title = vacancyDomain.title,
            address = vacancyDomain.address?.toNew(),
            company = vacancyDomain.company,
            experience = vacancyDomain.experience?.toNew(),
            publishedDate = vacancyDomain.publishedDate,
            salary = vacancyDomain.salary?.toNew() ,
            schedules = vacancyDomain.schedules,
            appliedNumber = vacancyDomain.appliedNumber,
            description = vacancyDomain.description,
            responsibilities = vacancyDomain.responsibilities,
            questions = vacancyDomain.questions,
            ids = 0,
            isFavorite = vacancyDomain.isFavorite
        )
    }
    private fun AddressDomain.toNew(): Address {
        return Address(
            town = this.town,
            street = this.street,
            house = this.house
        )
    }

    private fun ExperienceDomain.toNew(): Experience {
        return Experience(
            previewText = this.previewText,
            text = this.text
        )
    }
    private fun SalaryDomain.toNew(): Salary {
        return Salary(
            full = this.full
        )
    }
}