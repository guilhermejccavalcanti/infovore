package com.ontology2.bakemono.primitiveTriple;

import java.io.IOException;

import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import com.ontology2.bakemono.mappers.pse3.PSE3Mapper;
import com.ontology2.millipede.primitiveTriples.PrimitiveTriple;
import com.ontology2.millipede.primitiveTriples.PrimitiveTripleCodec;

public class PrimitiveTripleInputFormat extends FileInputFormat<LongWritable,PrimitiveTriple> {
    private static org.apache.commons.logging.Log logger = LogFactory.getLog(PrimitiveTripleInputFormat.class);
    final static PrimitiveTripleCodec ptc=new PrimitiveTripleCodec();
    
    @Override
    public RecordReader<LongWritable, PrimitiveTriple> createRecordReader(
            final InputSplit split, final TaskAttemptContext context) throws IOException,
            InterruptedException {
        return new LineProcessingRecordReader<PrimitiveTriple>() {

            @Override
            PrimitiveTriple convert(Text line) {
                logger.info("tried to read string "+line);
                return ptc.decode(line.toString());
            }
            
        };
    }

}
