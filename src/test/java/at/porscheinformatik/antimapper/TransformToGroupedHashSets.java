package at.porscheinformatik.antimapper;

import static at.porscheinformatik.antimapper.TestUtils.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class TransformToGroupedHashSets extends AbstractMapperTest
{

    @Test
    public void testNullToGroupedHashSets()
    {
        Map<Character, Set<String>> dtos =
            this.transformAll((List<char[]>) null, BOARDING_PASS).toGroupedHashSets(GROUPER);

        assertThat(dtos, nullValue());
    }

    @Test
    public void testNullToGroupedHashSetsOrEmpty()
    {
        Map<Character, Set<String>> dtos =
            this.transformAll((List<char[]>) null, BOARDING_PASS, Hint.OR_EMPTY).toGroupedHashSets(GROUPER);

        assertThat(dtos, is(Collections.emptyMap()));

        // check modifiable
        dtos.put('Z', TestUtils.toSet("Z"));
        assertThat(dtos.get('Z'), is(TestUtils.toSet("Z")));
    }

    @Test
    public void testToGroupedHashSets()
    {
        List<char[]> entities = toList("A1".toCharArray(), "A1".toCharArray(), "!B".toCharArray(), "C1".toCharArray(),
            "C2".toCharArray(), null);
        Map<Character, Set<String>> dtos = this.transformAll(entities, BOARDING_PASS).toGroupedHashSets(GROUPER);

        assertThat(dtos, matchesMap(toMap('A', is(toSet("A1")), 'C', is(toSet("C1", "C2")))));

        // check modifiable
        dtos.put('Z', TestUtils.toSet("Z"));
        assertThat(dtos.get('Z'), is(TestUtils.toSet("Z")));
        dtos.get('A').add("Z");
        assertThat(dtos.get('A'), hasItem(is("Z")));
    }

    @Test
    public void testToGroupedHashSetsKeepNullAndUnmodifiable()
    {
        List<char[]> entities = toList("A1".toCharArray(), "A1".toCharArray(), "!B".toCharArray(), "C1".toCharArray(),
            "C2".toCharArray(), null);
        Map<Character, Set<String>> dtos =
            this.transformAll(entities, Hint.KEEP_NULL, Hint.UNMODIFIABLE, BOARDING_PASS).toGroupedHashSets(GROUPER);

        assertThat(dtos, matchesMap(toMap((Character) null, is(toSet(new String[]{null})), 'A', is(toSet("A1", "A1")),
            'B', is(toSet(new String[]{null})), 'C', is(toSet("C1", "C2")))));

        try
        {
            dtos.put('Z', null);
            fail();
        }
        catch (UnsupportedOperationException e)
        {
            // expected
        }

        try
        {
            dtos.get('A').add("A2");
            fail();
        }
        catch (UnsupportedOperationException e)
        {
            // expected
        }
    }

}
