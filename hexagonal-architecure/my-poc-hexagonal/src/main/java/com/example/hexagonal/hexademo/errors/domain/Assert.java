package com.example.hexagonal.hexademo.errors.domain;

import java.util.Collection;
import java.util.Objects;
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
     * Ensure that the given collection is not empty
     *
     * @param field
     *          name of the field to check (will be displayed in exception message)
     * @param collection
     *          collection to check
     * @throws MissingMandatoryValueException
     *           if the collection is null or empty
     */
    public static void notEmpty(String field, Collection<?> collection) {
        field(field, collection).notEmpty();
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

    /**
     * Create a fluent asserter for {@link Collection}
     *
     * <p>
     * Usage:
     * </p>
     *
     * <pre>
     * <code>
     * Assert.field("name", name)
     *  .notEmpty()
     *  .maxSize(150);
     * </code>
     * </pre>
     *
     * @param field
     *          name of the field to check (will be displayed in exception message)
     * @param input
     *          collection to check
     * @return A {@link CollectionAsserter} for this field and value
     */
    public static <T> CollectionAsserter<T> field(String field, Collection<T> input) {
        return new CollectionAsserter<>(field, input);
    }

    /**
     * Asserter dedicated to {@link Collection} assertions
     */
    public static class CollectionAsserter<T> {

        private final String field;
        private final Collection<T> value;

        private CollectionAsserter(String field, Collection<T> value) {
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
        public CollectionAsserter<T> notNull() {
            Assert.notNull(field, value);

            return this;
        }

        /**
         * Ensure that the value is not empty (null or empty)
         *
         * @return The current asserter
         * @throws MissingMandatoryValueException
         *           if the value is null or empty
         */
        public CollectionAsserter<T> notEmpty() {
            notNull();

            if (value.isEmpty()) {
                throw MissingMandatoryValueException.forEmptyValue(field, field + "." + "empty");
            }

            return this;
        }
    }
}
