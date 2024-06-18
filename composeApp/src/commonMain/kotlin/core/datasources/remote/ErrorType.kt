package datasources.remote

enum class ErrorType {
    ACCESS_DENIED,
    CANCELLED,
    NETWORK_ERROR,
    TIMED_OUT,
    NO_RESULT,
    DATABASE_ERROR,
    UNKNOWN,
    REDIRECT,
    INTERNAL_ERROR,
}
