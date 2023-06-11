package com.example.hexagonal.hexademo.errors.domain;

import java.util.Optional;

public class Assert {

    private Assert() {}

    public static void notNull(String fieldName, Object fieldValue) {
        if (Optional.ofNullable(fieldValue).isEmpty()) {
            throw MissingMandatoryValueException.forNullValue(fieldName, fieldName + "." + "null");
        }
    }

    /**
     * Ensure that the value is not blank (null, empty or only whitespace)
     *
     * @param field
     *          name of the field to check (will be displayed in exception message)
     * @param input
     *          input to check
     * @throws MissingMandatoryValueException
     *           if the input is blank
     */
    public static void notBlank(String field, String input) {
        Assert.field(field, input).notBlank();
    }

    /**
     * Create a fluent asserter for {@link String}
     *
     * <p>
     * Usage:
     * </p>
     *
     * <pre>
     * <code>
     * Assert.field("name", name)
     *   .notBlank()
     *   .maxLength(150);
     * </code>
     * </pre>
     *
     * @param field
     *          name of the field to check (will be displayed in exception message)
     * @param input
     *          string to check
     * @return A {@link StringAsserter} for this field and value
     */
    public static StringAsserter field(String field, String input) {
        return new StringAsserter(field, input);
    }

    /**
     * Asserter dedicated to {@link String} assertions
     */
    public static class StringAsserter {

        private final String field;
        private final String value;

        private StringAsserter(String field, String value) {
            this.field = field;
            this.value = value;
        }

        /**
         * Ensure that the value is not null
         *
         * @return The current asserter
         * @throws MissingMandatoryValueException
         *           if the value is null
         */
        public StringAsserter notNull() {
            Assert.notNull(field, value);

            return this;
        }

        /**
         * Ensure that the value is not blank (null, empty or only whitespace)
         *
         * @return The current asserter
         * @throws MissingMandatoryValueException
         *           if the value is blank
         */
        public StringAsserter notBlank() {
            notNull();

            if (value.isBlank()) {
                throw MissingMandatoryValueException.forBlankValue(field, field + "." + "blank");
            }

            return this;
        }
    }
}
