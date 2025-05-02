public class PhotoManager {
    BST<LinkedList<Photo>> index;
    LinkedList<Photo> allPhotos;

    // Constructor
    public PhotoManager() {
        index = new BST<LinkedList<Photo>>();
        allPhotos = new LinkedList<Photo>();
    }

    // Add a photo
    public void addPhoto(Photo p) {
        if(Photo_Exist(allPhotos, p)) return; // to prevent repetition
        allPhotos.insert(p);
        LinkedList<String> tags = p.getTags();
        if(tags.empty()) return; // you can omit this
        tags.findFirst();
        
        while(!tags.last()) {
            // for current tags except last
            String curTag = tags.retrieve();
            boolean found = index.findKey(curTag);
            if (!found) {
                LinkedList<Photo> curPhotos = new LinkedList<Photo>();
                curPhotos.insert(p);
                index.insert(curTag, curPhotos);
            } else {
                LinkedList<Photo> curPhotos = index.retrieve();
                curPhotos.insert(p);
            }
            tags.findNext();
        }
        
        // for last element
        String curTag = tags.retrieve();
        boolean found = index.findKey(curTag);
        if (!found) {
            LinkedList<Photo> curPhotos = new LinkedList<Photo>();
            curPhotos.insert(p);
            index.insert(curTag, curPhotos);
        } else {
            LinkedList<Photo> curPhotos = index.retrieve();
            curPhotos.insert(p);
        }
    }

    private boolean Photo_Exist(LinkedList<Photo> allPhotos2, Photo p) {
		// TODO Auto-generated method stub
		return false;
	}

	// Remove a photo from a list
    public void remove_From_List(LinkedList<Photo> L, Photo p) {
        if(L.empty()) return;
        L.findFirst();
        while(!L.empty() && !L.last()) {
            if(L.retrieve().path.equals(p.path))
                L.remove();
            else
                L.findNext();
        }
        if(!L.empty() && L.retrieve().path.equals(p.path))
            L.remove();
    }

    // Delete a photo by path
    public void deletePhoto(String path) {
        if(allPhotos.empty()) return;
        LinkedList<String> tags = null;
        allPhotos.findFirst();
        
        while(!allPhotos.last()) {
            if(allPhotos.retrieve().path.equals(path)) {
                tags = allPhotos.retrieve().tags;
                allPhotos.remove();
            } else {
                allPhotos.findNext();
            }
        }
        if(allPhotos.retrieve().path.equals(path)) {
            tags = allPhotos.retrieve().tags;
            allPhotos.remove();
        }
        
        // Deleting tags from index
        if(tags == null || tags.empty()) return;
        Photo ourPhoto = new Photo(path, tags);
        tags.findFirst();
        
        while(!tags.last()) {
            if(index.findKey(tags.retrieve())) {
                remove_From_List(index.retrieve(), ourPhoto);
                if(index.retrieve().empty())
                    index.removeKey(tags.retrieve());
            }
            tags.findNext();
        }
        
        // For the last tag
        if(index.findKey(tags.retrieve())) {
            remove_From_List(index.retrieve(), ourPhoto);
            if(index.retrieve().empty())
                index.removeKey(tags.retrieve());
        }
    }

    // Return the inverted index of all managed photos
    public BST<LinkedList<Photo>> getPhotos() {
        return index;
    }

    // helping methods
}