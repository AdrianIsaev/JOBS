package ru.bestapps.data.data.repository


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.bestapps.data.data.contracts.VacancyApi
import ru.bestapps.data.data.storage.models.Address
import ru.bestapps.data.data.storage.models.Experience
import ru.bestapps.data.data.storage.models.Salary
import ru.bestapps.domain.repository.VacancyRepository
import ru.bestapps.data.data.storage.models.Vacancy
import ru.bestapps.domain.models.AddressDomain
import ru.bestapps.domain.models.ExperienceDomain
import ru.bestapps.domain.models.SalaryDomain
import ru.bestapps.domain.models.VacancyDomain

class VacancyRepositoryImpl(private val retrofit: VacancyApi): VacancyRepository {
    override suspend fun getVacancies(): List<VacancyDomain> {
        val response = retrofit.getVacancies()
        val domainLayerModelList = mutableListOf<VacancyDomain>()
            for (i in response.vacancies) {
                domainLayerModelList.add(helper(i))
            }


        return domainLayerModelList
    }

    private fun helper(vacancy: Vacancy): VacancyDomain {
        return VacancyDomain(
            id = vacancy.id,
            lookingNumber = vacancy.lookingNumber,
            title = vacancy.title,
            address = vacancy.address?.toDomain(),
            company = vacancy.company,
            experience = vacancy.experience?.toDomain(),
            publishedDate = vacancy.publishedDate,
            salary = vacancy.salary?.toDomain(),
            schedules = vacancy.schedules,
            appliedNumber = vacancy.appliedNumber,
            description = vacancy.description,
            responsibilities = vacancy.responsibilities,
            questions = vacancy.questions,
            isFavorite = false,
            ids = 0
        )
    }
    private fun Address.toDomain(): AddressDomain {
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