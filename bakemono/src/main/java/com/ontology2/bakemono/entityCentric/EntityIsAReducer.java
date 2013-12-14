package com.ontology2.bakemono.entityCentric;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import com.ontology2.bakemono.primitiveTriples.PrimitiveTriple;
import com.ontology2.bakemono.primitiveTriples.PrimitiveTripleCodec;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;

import java.util.Set;

public class EntityIsAReducer extends EntityMatchesRuleReducer<Text,Text> {
    Set<String> typeList;

    final static PrimitiveTripleCodec codec=new PrimitiveTripleCodec();
    final static String A="<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>";
    public static final String IS_A="com.ontology2.bakemono.entityCentric.EntityIsAReducer";
    public static final String TYPE_LIST=IS_A+".typeList";

    @Override
    public void setup(Context context) {
        Configuration that=context.getConfiguration();
        typeList= Sets.newHashSet(Splitter.on(",").split(that.get(TYPE_LIST)));
    }

    @Override
    protected boolean matches(Text subject, Iterable<Text> facts) {
        for(Text fact:facts) {
            PrimitiveTriple pt=codec.decode(fact.toString());
            if (A.equals(pt.getPredicate()) &&  typeList.contains(pt.getObject()))
                return true;
        }
        return false;
    }
}
