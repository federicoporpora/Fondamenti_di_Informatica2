package trainstats.persistence;

import java.io.IOException;
import java.io.Reader;

import trainstats.model.Train;

public interface TrainReader {
	public Train readTrain(Reader rdr) throws IOException;
}