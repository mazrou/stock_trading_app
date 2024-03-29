package com.mazrou.toDoApp.business.domain.uitils

import com.mazrou.toDoApp.business.domain.uitils.Response
import com.mazrou.toDoApp.business.domain.uitils.StateMessage


data class DataState<T>(
    val stateMessage: StateMessage? = null,
    val data: T? = null,
    val isLoading: Boolean = false
) {

    companion object {

        fun <T> error(
            response: Response,
        ): DataState<T> {
            return DataState(
                stateMessage = StateMessage(
                    response
                ),
                data = null,
            )
        }

        fun <T> data(
            response: Response?,
            data: T? = null,
        ): DataState<T> {
            return DataState(
                stateMessage = response?.let {
                    StateMessage(
                        it
                    )
                },
                data = data,
            )
        }

        fun <T> loading(): DataState<T> = DataState(isLoading = true)
    }
}