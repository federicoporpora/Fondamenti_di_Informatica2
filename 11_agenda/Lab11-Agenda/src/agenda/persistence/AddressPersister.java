package agenda.persistence;

import java.util.StringTokenizer;

import agenda.model.Address;
import agenda.model.Detail;
import agenda.model.DetailFactory;

public class AddressPersister implements DetailPersister {
	private final static String SEPARATOR = ";";

	@Override
	public Detail load(StringTokenizer source) throws BadFileFormatException{
		Address a = (Address) DetailFactory.of("Address");
		//controllo generale sul numero di token, si potrebbero affinare i controlli
		if(source.countTokens()!=6)
			throw new BadFileFormatException("Email: not enough tokens");
		a.setDescription(source.nextToken(SEPARATOR));
		a.setStreet(source.nextToken(SEPARATOR));
		a.setNumber(source.nextToken(SEPARATOR));
		a.setZipCode(source.nextToken(SEPARATOR));
		a.setCity(source.nextToken(SEPARATOR));
		a.setState(source.nextToken());
		return a;
	}

	@Override
	public void save(Detail d, StringBuilder sb) {
		Address a = (Address) d;
		sb.append(a.getName());
		sb.append(SEPARATOR);
		sb.append(a.getDescription());
		sb.append(SEPARATOR);
		sb.append(a.getStreet());
		sb.append(SEPARATOR);
		sb.append(a.getNumber());
		sb.append(SEPARATOR);
		sb.append(a.getZipCode());
		sb.append(SEPARATOR);
		sb.append(a.getCity());
		sb.append(SEPARATOR);
		sb.append(a.getState());
		sb.append(FileUtils.NEWLINE);
	}

}
