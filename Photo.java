public class Photo {

    private String path;
    LinkedList<String> allTags = new LinkedList<>();

    public Photo(String path, LinkedList<String> tags)
    {
        this.path = path;

        if (! tags.empty())
        {
            tags.findFirst();
            while ( !tags.last())
                allTags.insert(tags.retrieve());
                tags.findNext();
            }
            allTags.insert(tags.retrieve());/
        }
    }

    public String getPath()
    {
        return path;
    }

    public LinkedList<String> getTags()
    {
        LinkedList<String> tagsNew = new LinkedList<String>();

        if (! allTags.empty())
        {
            allTags.findFirst();
            while ( ! allTags.last())
            {
                tagsNew.insert(allTags.retrieve());
                allTags.findNext();
            }
            tagsNew.insert(allTags.retrieve());
        }
        return tagsNew;
    }

    @Override
    public String toString() {

        String str = "Photo{" + "path=" + path + ", allTags=" ;

        allTags.findFirst();
        while ( ! allTags.last())
        {
            str += allTags.retrieve().toString() + "; ";
            allTags.findNext();
        }

        str += allTags.retrieve().toString()  + "}";
        return str ;
    }
}
