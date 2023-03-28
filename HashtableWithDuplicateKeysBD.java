// --== CS400 Project One File Header ==--
// Name: Ziqi Liao
// CSL Username: zliao
// Email: zliao47@wisc.edu
// Lecture #: Section 3: MoWeFr 1:20PM - 2:10PM, Florian Heimerl
// Notes to Grader: <any optional extra notes to your grader>

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.NoSuchElementException;

public class HashtableWithDuplicateKeysBD<KeyType, ValueType> extends HashtableMap<KeyType, List<ValueType>> implements HashtableWithDuplicateKeysInterface<KeyType, ValueType>{
	
//	@Override
	@SuppressWarnings("unchecked")
	public List<ValueType> get(KeyType key) throws NoSuchElementException {

		List<ValueType> postList = new ArrayList<>();
		
		PostBD post1 = new PostBD("TITLE:title1 title2,URL:url1/url2,BODY:body1 body2");
		PostBD post2 = new PostBD("TITLE:title1 title4,URL:url3/url4,BODY:body3 body1");
		
		postList.add((ValueType) post1);
		postList.add((ValueType) post2);
		
		return postList;
		
		
	}

	@Override
	public void putOne(KeyType key, ValueType value) {
		
		HashMap<KeyType, List<ValueType>> map = new HashMap<KeyType, List<ValueType>>();

		if(map.containsKey(key)) {
			map.get(key).add(value); // value  be added to the existing collection associated with the key.			
		} else {
			List<ValueType> lists = new ArrayList<>();
			lists.add(value); 
			map.put(key, lists);			
		}
	}

	@Override
	public void removeOne(KeyType key, ValueType value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getNumberOfValues() {
		// TODO Auto-generated method stub	
		return 0;
	}


}
