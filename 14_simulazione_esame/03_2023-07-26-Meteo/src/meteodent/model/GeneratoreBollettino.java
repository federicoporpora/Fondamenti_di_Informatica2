package meteodent.model;

import java.util.List;

public interface GeneratoreBollettino {
	Bollettino generaBollettino(List<Previsione> previsioni, TipoBollettino tipoBollettino);
}
