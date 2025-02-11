/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0; you may not use this file except in compliance with the Elastic License
 * 2.0.
 */

package org.elasticsearch.xpack.esql.expression.function.aggregate;

import org.elasticsearch.compute.aggregation.AggregatorFunctionSupplier;
import org.elasticsearch.compute.aggregation.MedianAbsoluteDeviationDoubleAggregatorFunctionSupplier;
import org.elasticsearch.compute.aggregation.MedianAbsoluteDeviationIntAggregatorFunctionSupplier;
import org.elasticsearch.compute.aggregation.MedianAbsoluteDeviationLongAggregatorFunctionSupplier;
import org.elasticsearch.xpack.ql.expression.Expression;
import org.elasticsearch.xpack.ql.tree.NodeInfo;
import org.elasticsearch.xpack.ql.tree.Source;

import java.util.List;

public class MedianAbsoluteDeviation extends NumericAggregate {

    // TODO: Add parameter
    public MedianAbsoluteDeviation(Source source, Expression field) {
        super(source, field);
    }

    @Override
    protected NodeInfo<MedianAbsoluteDeviation> info() {
        return NodeInfo.create(this, MedianAbsoluteDeviation::new, field());
    }

    @Override
    public MedianAbsoluteDeviation replaceChildren(List<Expression> newChildren) {
        return new MedianAbsoluteDeviation(source(), newChildren.get(0));
    }

    @Override
    protected AggregatorFunctionSupplier longSupplier(List<Integer> inputChannels) {
        return new MedianAbsoluteDeviationLongAggregatorFunctionSupplier(inputChannels);
    }

    @Override
    protected AggregatorFunctionSupplier intSupplier(List<Integer> inputChannels) {
        return new MedianAbsoluteDeviationIntAggregatorFunctionSupplier(inputChannels);
    }

    @Override
    protected AggregatorFunctionSupplier doubleSupplier(List<Integer> inputChannels) {
        return new MedianAbsoluteDeviationDoubleAggregatorFunctionSupplier(inputChannels);
    }
}
