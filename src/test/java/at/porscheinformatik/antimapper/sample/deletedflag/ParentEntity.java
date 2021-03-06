package at.porscheinformatik.antimapper.sample.deletedflag;

import static at.porscheinformatik.antimapper.TestUtils.*;

import java.util.SortedSet;

public class ParentEntity
{

    private Integer id;
    private String name;
    private SortedSet<ChildEntity> childs;
    private boolean deleted;

    public ParentEntity()
    {
        super();
    }

    public ParentEntity(Integer id)
    {
        super();

        this.id = id;
    }

    public ParentEntity(Integer id, String name, ChildEntity... childs)
    {
        super();

        this.id = id;
        this.name = name;

        this.childs = toSortedSet(childs);

        for (ChildEntity child : childs)
        {
            child.setParent(this);
        }
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public SortedSet<ChildEntity> getChilds()
    {
        return childs;
    }

    public void setChilds(SortedSet<ChildEntity> childs)
    {
        this.childs = childs;
    }

    public boolean isDeleted()
    {
        return deleted;
    }

    public void setDeleted(boolean deleted)
    {
        this.deleted = deleted;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder("ParentEntity (id=")
            .append(id)
            .append(", name=")
            .append(name)
            .append(", deleted=")
            .append(deleted)
            .append(") {");

        for (ChildEntity child : childs)
        {
            builder.append("\n\t").append(child);
        }

        builder.append("\n}");

        return builder.toString();
    }

}
