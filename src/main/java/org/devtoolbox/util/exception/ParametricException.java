/*
 * MIT License
 *
 * Copyright © 2020-2023 dev-toolbox.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
 * USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.devtoolbox.util.exception;

import java.time.OffsetDateTime;

import org.slf4j.helpers.MessageFormatter;

/**
 * <p>Defines an exception using an identifier and optional parameters.</p>
 * <p>The additional functionalities compared to base exceptions are :</p>
 * <ul>
 *    <li>an identifier that enables to clearly identify the error</li>
 *    <li>additional data that can be used for the default error message or retrieved for error handling :<ul>
 *        <li>the creation time</li>
 *        <li>an optional collection of user-defined parameters</li>
 *    </ul></li>
 * </ul>
 * <p>Parametric exceptions uses the MessageFormatter from slf4j, which replaces {} by parameters.</p>
 *
 * @author Arnaud Lecollaire
 */
public interface ParametricException {

    /** Gets the time of this exception's creation. */
    OffsetDateTime getCreationTime();

    /** Gets the identifier of this exception. */
    ErrorIdentifier getIdentifier();

    /** Gets this exception's parameters (can be null). */
    Object[] getParameters();

    String getLocalizedMessage();

    default String defaultToString() {
        final String classDate = getClass().getName() + " (" + getCreationTime() + ')';
        final String message = getLocalizedMessage();
        return (message != null) ? (classDate + " : " + message) : classDate;
    }

    public static class Formatter {

        /**
         * Formats an error message using the template from the error identifier and parameters.
         * This formatter implementation uses SLF4J's MessageFormatter
         *
         * @see org.slf4j.helpers.MessageFormatter
         * @param identifier error identifier containing the message template
         * @param parameters optional parameters
         * @return the formatted message
         */
        public static String formatMessage(final ErrorIdentifier identifier, final Object... parameters) {
            return MessageFormatter.arrayFormat(identifier.getDefaultMessage(), parameters).getMessage();
        }
    }
}