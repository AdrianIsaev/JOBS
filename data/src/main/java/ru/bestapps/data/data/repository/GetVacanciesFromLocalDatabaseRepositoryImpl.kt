package ru.bestapps.data.data.repository

import ru.bestapps.data.data.storage.models.Address
import ru.bestapps.data.data.storage.models.Experience
import ru.bestapps.data.data.storage.models.Salary
import ru.bestapps.data.data.storage.room.dao.GetVacanciesDao
import ru.bestapps.data.data.storage.room.entity.Vacancy
import ru.bestapps.domain.models.AddressDomain
import ru.bestapps.domain.models.ExperienceDomain
import ru.bestapps.domain.models.SalaryDomain
import ru.bestapps.domain.models.VacancyDomain
import ru.bestapps.domain.repository.GetVacanciesFromLocalDatabaseRepository

class GetVacanciesFromLocalDatabaseRepositoryImpl(private val getVacanciesDao: GetVacanciesDao): GetVacanciesFromLocalDatabaseRepository {

    override suspend fun getAllVacancies(): List<VacancyDomain> {
        val vacancies = getVacanciesDao.getAllVacancies()
        val domainVacancies = mutableListOf<VacancyDomain>()
        for (vacancy in vacancies){
            domainVacancies.add(helperToDomain(vacancy))
        }
        return domainVacancies
    }
    private fun helperToDomain(vacancy: Vacancy): VacancyDomain {
        return VacancyDomain(
            id = vacancy.id,
            lookingNumber = vacancy.lookingNumber,
            title = vacancy.title,
            address = vacancy.address?.toDomain(),
            company = vacancy.company,
            experience = vacancy.experience?.toDomain(),
            publishedDate = vacancy.publishedDate,
            salary = vacancy.salary?.toDomain() ,
            schedules = vacancy.schedules,
            appliedNumber = vacancy.appliedNumber,
            description = vacancy.description,
            responsibilities = vacancy.responsibilities,
            questions = vacancy.questions,
            isFavorite = vacancy.isFavorite,
            ids = vacancy.ids
        )
    }
    private fun Address.toDomain(): AddressDomain{
        return AddressDomain(
            town = this.town,
            street = this.street,
            house = this.house
        )
    }

    private fun Experience.toDomain(): ExperienceDomain {
        return ExperienceDomain(
            previewText = this.previewText,
            text = this.text
        )
    }

    private fun Salary.toDomain(): SalaryDomain {
        return SalaryDomain(
            full = this.full
        )
    }
}