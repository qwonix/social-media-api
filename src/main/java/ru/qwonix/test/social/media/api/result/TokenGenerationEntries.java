package ru.qwonix.test.social.media.api.result;

public record TokenGenerationEntries() {

    public sealed interface Result {

        record Fail(String message) implements Result {
        }

        record Success(String token) implements Result {
        }
    }
}
