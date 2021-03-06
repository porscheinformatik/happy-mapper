package at.porscheinformatik.antimapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Supplier;

/**
 * Transforms paired values. Paired values are most often used for grouping, where the left value is the key and the
 * right value is the entity.
 *
 * @author HAM
 *
 * @param <DTO> the type of DTO
 * @param <GroupKey> the type of the group key
 * @param <Entity> the type of Entity
 */
public interface PairTransformer<DTO, GroupKey, Entity> extends StreamTransformer<DTO, Entity>
{

    /**
     * Transforms a the grouped map to a grouped {@link Map} of DTOs. Ignores entities that transform to null, unless
     * the {@link Hint#KEEP_NULL} hint is set. Returns an unmodifiable instance if the {@link Hint#UNMODIFIABLE} is set.
     * Never returns null if the {@link Hint#OR_EMPTY} is set. The key entities map key is added to the hints for each
     * transformation round.
     *
     * @param <DTOCollection> the type of the collections in the result map
     * @param <DTOMap> the type of map
     * @param mapFactory a factory for the result map
     * @param collectionFactory a factory for the collections in the result map
     * @return a map
     */
    <DTOCollection extends Collection<DTO>, DTOMap extends Map<GroupKey, DTOCollection>> Map<GroupKey, DTOCollection> toGroupedMap(
        Supplier<DTOMap> mapFactory, Supplier<DTOCollection> collectionFactory);

    /**
     * Transforms a grouped map with an {@link Iterable} of entities to a grouped {@link HashMap} with an
     * {@link ArrayList} of DTOs. Ignores entities that transform to null, unless the {@link Hint#KEEP_NULL} hint is
     * set. Returns an unmodifiable instance if the {@link Hint#UNMODIFIABLE} is set. Never returns null if the
     * {@link Hint#OR_EMPTY} is set. The key entities map key is added to the hints for each transformation round.
     *
     * @return a map
     */
    default Map<GroupKey, List<DTO>> toGroupedArrayLists()
    {
        return toGroupedMap(HashMap<GroupKey, List<DTO>>::new, ArrayList<DTO>::new);
    }

    /**
     * Transforms a grouped map with an {@link Iterable} of entities to a grouped {@link HashMap} with an
     * {@link HashSet} of DTOs. Ignores entities that transform to null, unless the {@link Hint#KEEP_NULL} hint is set.
     * Returns an unmodifiable instance if the {@link Hint#UNMODIFIABLE} is set. Never returns null if the
     * {@link Hint#OR_EMPTY} is set. The key entities map key is added to the hints for each transformation round.
     *
     * @return a map
     */
    default Map<GroupKey, Set<DTO>> toGroupedHashSets()
    {
        return toGroupedMap(HashMap<GroupKey, Set<DTO>>::new, HashSet<DTO>::new);
    }

    /**
     * Transforms a grouped map with an {@link Iterable} of entities to a grouped {@link LinkedHashMap} with an
     * {@link HashSet} of DTOs. Ignores entities that transform to null, unless the {@link Hint#KEEP_NULL} hint is set.
     * Returns an unmodifiable instance if the {@link Hint#UNMODIFIABLE} is set. Never returns null if the
     * {@link Hint#OR_EMPTY} is set. The key entities map key is added to the hints for each transformation round.
     *
     * @return a map
     */
    default Map<GroupKey, Set<DTO>> toGroupedLinkedHashSets()
    {
        return toGroupedMap(HashMap<GroupKey, Set<DTO>>::new, LinkedHashSet<DTO>::new);
    }

    /**
     * Transforms a grouped map with an {@link Iterable} of entities to a grouped {@link HashMap} with an
     * {@link TreeSet} of DTOs. Ignores entities that transform to null, unless the {@link Hint#KEEP_NULL} hint is set.
     * Returns an unmodifiable instance if the {@link Hint#UNMODIFIABLE} is set. Never returns null if the
     * {@link Hint#OR_EMPTY} is set. The key entities map key is added to the hints for each transformation round.
     *
     * @return a map
     */
    default Map<GroupKey, SortedSet<DTO>> toGroupedTreeSets()
    {
        return toGroupedMap(HashMap<GroupKey, SortedSet<DTO>>::new, TreeSet<DTO>::new);
    }

    /**
     * Transforms a grouped map with an {@link Iterable} of entities to a grouped {@link HashMap} with an
     * {@link TreeSet} of DTOs. Ignores entities that transform to null, unless the {@link Hint#KEEP_NULL} hint is set.
     * Returns an unmodifiable instance if the {@link Hint#UNMODIFIABLE} is set. Never returns null if the
     * {@link Hint#OR_EMPTY} is set. The key entities map key is added to the hints for each transformation round.
     *
     * @param comparator the comparator for the tree set
     * @return a map
     */
    default Map<GroupKey, SortedSet<DTO>> toGroupedTreeSets(Comparator<? super DTO> comparator)
    {
        return toGroupedMap(HashMap<GroupKey, SortedSet<DTO>>::new, () -> new TreeSet<>(comparator));
    }

}