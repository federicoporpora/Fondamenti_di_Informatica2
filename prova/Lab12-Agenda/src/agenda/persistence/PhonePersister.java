package agenda.persistence;

import java.util.StringTokenizer;

import agenda.model.Detail;
import agenda.model.DetailFactory;
import agenda.model.Phone;

public class PhonePersister implements DetailPersister {
	private final static String SEPARATOR = ";";

	@Override
	public Detail load(StringTokenizer source) throws BadFileFormatException {
		Phone phone = (Phone) DetailFactory.of("Phone");
		//controllo generale sul numero di token, si potrebbero affinare i controlli
		if(source.countTokens()!=2)
			throw new BadFileFormatException("Phone: not enough tokens");
		phone.setDescription(source.nextToken(SEPARATOR));
		phone.setValue(source.nextToken());
		return phone;
	}

	@Override
	public void save(Detail d, StringBuilder sb) {
		Phone phone = (Phone) d;
		sb.append(phone.getName());
		sb.append(SEPARATOR);
		sb.append(phone.getDescription());
		sb.append(SEPARATOR);
		sb.append(phone.getValue());
		sb.append(FileUtils.NEWLINE);
	}

}
