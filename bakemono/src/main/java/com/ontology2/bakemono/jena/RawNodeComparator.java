package com.ontology2.bakemono.jena;

import org.apache.hadoop.io.RawComparator;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import static org.apache.hadoop.io.WritableComparable.*;

public class RawNodeComparator extends WritableComparator {

    protected RawNodeComparator( boolean createInstances) {
        super(WritableNode.class, createInstances);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        WritableNode $a=(WritableNode) a;
        WritableNode $b=(WritableNode) b;
        return $a.compareTo($b);
    }
}
