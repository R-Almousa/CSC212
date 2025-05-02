
public class Album {
    String name;
    String condition;
    PhotoManager manager;
  //  InvIndexPhotoManager manager;
    
    // Constructor
    public Album(String name, String condition, PhotoManager manager) {
        this.name = name;
        this.condition = condition;
        this.manager = manager;
    }

    // Return the name of the album
    public String getName() {
        return name;
    }

    // Return the condition associated with the album
    public String getCondition() {
        return condition;
    }

    // Return the manager
    public PhotoManager getManager() {
        return manager;
    }

    // Check if a photo exists in a linked list
    public boolean Photo_Exist(LinkedList<Photo> L, Photo p) {
        if(L.empty()) 
            return false;
            
        L.findFirst();
        while(!L.last()) {
            if(L.retrieve().path.equals(p.path))
                return true;
            L.findNext();
        }
        if(L.retrieve().path.equals(p.path))
            return true;
        return false;
    }

    // Return all photos that satisfy the album condition
    public LinkedList<Photo> getPhotos() {
        LinkedList<Photo> res = new LinkedList<Photo>();
        if(condition == null) 
            return res;

        BST<LinkedList<Photo>> index = manager.getPhotos();
        String[] a = condition.split("AND");
        
        // يجب إكمال هذا الجزء حسب منطق تطبيقك
        // ...
        
        return res;
    }
}





