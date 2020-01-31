package utils;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;

public class DtoUtils {
	
	public static <T,U> Set<U> setFromEntityStream (
			Stream<T> stream,
			ModelMapper mapper,
			Class<U> classDto) {
		
		return stream
				.map(entity->mapper.map(entity, classDto))
				.collect(Collectors.toSet());
	}
	
	public static <T,U> List<U> listFromEntityStream (
			Stream<T> stream,
			ModelMapper mapper,
			Class<U> classDto) {
		
		return stream
				.map(entity->mapper.map(entity, classDto))
				.collect(Collectors.toList());
	}
	
	public static <T, U, C extends Collection<U>> C collectionFromEntityStream(
			Stream<T> stream,
			ModelMapper mapper,
			Class<U> classDto,
			Supplier<C> supplier) {
		return stream
				.map(entity->mapper.map(entity, classDto))
				.collect(Collectors.toCollection(supplier));
	}

	
}