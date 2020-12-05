/*
 * MIT License
 *
 * Copyright © 2020 dev-toolbox.org
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
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Checked implementation of a <code>ParametricException</code>.
 *
 * @see org.devtoolbox.util.exception.ParametricException
 * @author Arnaud Lecollaire
 */
//TODO add javadoc
public class ParametricCheckedException extends Exception implements ParametricException {

    private static final long serialVersionUID = 6553773363350453967L;

    private static final Logger LOGGER = LoggerFactory.getLogger(ParametricCheckedException.class);

    private final OffsetDateTime creationTime = OffsetDateTime.now();
    private final ErrorIdentifier identifier;
    private final Object[] parameters;


    public ParametricCheckedException(final ErrorIdentifier identifier) {
        this(identifier, null, (Object[]) null);
        LOGGER.debug("Initialized new parametric exception : " + this);
    }

    public ParametricCheckedException(final ErrorIdentifier identifier, final Throwable cause) {
        this(identifier, cause, (Object[]) null);
        LOGGER.debug("Initialized new parametric exception : " + this);
    }

    public ParametricCheckedException(final ErrorIdentifier identifier, final Object ... parameters) {
        super(Formatter.formatMessage(identifier, parameters));
        this.identifier = identifier;
        this.parameters = parameters;
    }

    public ParametricCheckedException(final ErrorIdentifier identifier, final Throwable cause, final Object ... parameters) {
        super(Formatter.formatMessage(identifier, parameters), cause);
        this.identifier = identifier;
        this.parameters = parameters;
    }

    @Override
    public OffsetDateTime getCreationTime() {
        return creationTime;
    }

    @Override
    public ErrorIdentifier getIdentifier() {
        return identifier;
    }

    @Override
    public Object[] getParameters() {
        return (parameters == null) ? null : Arrays.copyOf(parameters, parameters.length);
    }

    @Override
    public String toString() {
        return defaultToString();
    }
}