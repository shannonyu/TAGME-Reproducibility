/*
 * Copyright 2011 Carnegie Mellon University
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package edu.cmu.lti.wikipedia_redirect;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents the wikipedia hypernym data e.g. ones generated by
 * <a href="http://alaginrc.nict.go.jp/hyponymy/index.html">NICT's "Hyponymy extraction tool"</a>
 * 
 * @author Hideki Shima
 */
public class WikipediaHypernym extends HashMap<String,List<String>>
 implements Serializable {

  private static final long serialVersionUID = 20111019L;

  public WikipediaHypernym( int size ) {
    // RAM (heap) efficient capacity setting
    super( size * 4 / 3 + 1 );
  }
  
  public void load( File file ) throws Exception {
    WikipediaHypernym wh = IOUtil.loadWikipediaHypernym(file);
    for ( String key : wh.keySet() ) {
      List<String> thisList = get(key);
      List<String> newList = wh.get(key);
      if ( thisList != null ) {
        thisList.addAll( newList );
      } else {
        thisList = new ArrayList<String>( newList );
      }
      put(key, thisList);
    }
  }
  
}
