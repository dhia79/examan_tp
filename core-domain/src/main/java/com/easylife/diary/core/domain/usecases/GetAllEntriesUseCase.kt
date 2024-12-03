package com.easylife.diary.core.domain.usecases

import com.easylife.diary.core.common.util.DiaryResult
import com.easylife.diary.core.common.util.dispatchers.DiaryDispatchers
import com.easylife.diary.core.data.mapper.EntryGroupMapper
import com.easylife.diary.core.data.repository.EntryRepository
import com.easylife.diary.core.model.EntryGroup
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by erenalpaslan on 9.01.2023
 */
class GetAllEntriesUseCase @Inject constructor(
    private val dispatchers: DiaryDispatchers,
    private val entryRepository: EntryRepository
): BaseUseCase<List<EntryGroup>, Unit>() {

    override suspend fun execute(params: Unit): Flow<DiaryResult<List<EntryGroup>?>> = flow {
        try {
            val entries = entryRepository.getAllEntries()
            emit(DiaryResult.Success(
                EntryGroupMapper.transform(entries)
            ))
        }catch (e: Exception) {
            emit(DiaryResult.Error(e.message))
        }
    }.flowOn(dispatchers.io)

}