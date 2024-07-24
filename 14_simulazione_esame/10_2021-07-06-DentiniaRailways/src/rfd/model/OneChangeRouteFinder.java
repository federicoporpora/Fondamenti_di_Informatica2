package rfd.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class OneChangeRouteFinder extends RouteFinder {

	public OneChangeRouteFinder(Set<RailwayLine> rwylines) {
		super(rwylines);
	}

	@Override
	public List<Route> getRoutes(String from, String to) {
		if (from == null || to == null) throw new IllegalArgumentException("bla bla null");
		if (from.equals(to)) return Collections.emptyList();
		List<Route> res = new ArrayList<>();
		rwylines.stream()
			.filter(railway -> railway.getPointOfInterest(from).isPresent() && railway.getPointOfInterest(to).isEmpty())
			.forEach(railway -> {});
	}

}