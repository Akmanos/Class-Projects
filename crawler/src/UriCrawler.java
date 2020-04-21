/*
 * Copyright 2017 Marc Liberatore.
 * Modified 2018 David Wemhoener
 */

package crawler;

import java.net.URI;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.jsoup.nodes.Document;

import document.RetrievedDocument;

/**
 * A simplified web crawler, specialized to crawl local URIs rather
 * than to retrieve remote documents.
 * 
 * @author liberato
 *
 */
public class UriCrawler {

	/**
	 * Instantiates a new UriCrawler. The maximum number of documents a crawler
	 * will attempt to visit, ever, is limited to visitQuota.
	 * 
	 * @param visitQuota
	 *            the maximum number of documents a crawler will attempt to
	 *            visit
	 * @throws IllegalArgumentException
	 *             if maximumRetrievalAttempts is less than one
	 */
	private int visitQuota;
	private Set<URI> uri;
	private Set<RetrievedDocument> retrievedDocs;
	private Set<URI> visitedUri;
	private Stack<URI> newStack;
	private Stack<URI> stack;
	
	public UriCrawler(int visitQuota) throws IllegalArgumentException {
		if (visitQuota < 1) {
			throw new IllegalArgumentException();
		}
		// TODO
		this.visitQuota = visitQuota;
		stack = new Stack<URI>();
		uri = new HashSet<URI>();
		retrievedDocs = new HashSet<RetrievedDocument>();
		visitedUri = new HashSet<URI>();
		newStack = new Stack<URI>();
	}

	/**
	 * Returns the set of URIs that this crawler has attempted to visit
	 * (successfully or not).
	 * 
	 * @return the set of URIs that this crawler has attempted to visit
	 */
	public Set<URI> getVistedUris() {
		// TODO
		return visitedUri;
	}
	
	/**
	 * Returns the set of RetrievedDocuments corresponding to the URIs
	 * this crawler has successfully visited.
	 * 
	 * @return the set of RetrievedDocuments corresponding to the URIs
	 * this crawler has successfully visited
	 */
	public Set<RetrievedDocument> getVisitedDocuments() {
		// TODO
		Iterator<URI> it = visitedUri.iterator();
		
		if(visitedUri != null || visitedUri.size()>0) {
			while(it.hasNext()) {
				URI u = it.next();
				
				Document doc = CrawlerUtils.parse(u);
				List<URI> list = CrawlerUtils.getFileUriLinks(doc);			
				retrievedDocs.add(new RetrievedDocument(u,doc.text() ,list));

			}
		}
		return retrievedDocs;
	}

	/**
	 * Adds a URI to the collections of URIs that this crawler should attempt to
	 * visit. Does not visit the URI.
	 * 
	 * @param uri
	 *            the URI to be visited (later!)
	 */
	public void addUri(URI uri) {
		// TODO
		
		if(uri != null) {
			this.uri.add(uri);
			stack.push(uri);
			newStack.push(uri);
		}
	}
	
	/**
	 * Retrieves a URI from the collections of URIs that this crawler should attempt to
	 * visit.
	 * 
	 * @return the next URI the crawler should attempt to visit.
	 */
	public URI getNext() {
		// TODO
		URI u = null;
		
		if(newStack.size() > 0 && newStack != null) {
			u = newStack.peek();
			newStack.pop();
		}
		
		return u;
	}
	

	/**
	 * Attempts to visit a single as-yet unattempted URI in this crawler's
	 * collection of to-be-visited URIs.
	 * 
	 * Visiting a document entails parsing the text and links from the URI.
	 * 
	 * If the parse succeeds:
	 * 
	 * - The "file:" links should be added to this crawler's collection of
	 * to-be-visited URIs.
	 * 
	 * - A new RetrievedDocument should be added to this crawler's collection of
	 * successfully visited documents.
	 * 
	 * If the parse fails, this method considers the visit attempted but
	 * unsuccessful.
	 * 
	 * @throws MaximumVisitsExceededException
	 *             if this crawler has already attempted to visit its quota of
	 *             visits
	 * @throws NoUnvisitedUrisException
	 *             if no more unattempted URI remain in this crawler's
	 *             collection of URIs to visit
	 */
	public void visitOne() throws MaximumVisitsExceededException, NoUnvisitedUrisException {
		// TODO
		if(visitQuota <= 0) {
			throw new MaximumVisitsExceededException();
		}
		
		if(newStack.size() == 0 || noUnvisited()) {
			throw new NoUnvisitedUrisException();
		}
		
		URI u= this.getNext();
		
		if(u != null && CrawlerUtils.parse(u) != null) {
			Document doc = CrawlerUtils.parse(u);
			visitedUri.add(u);
			List<URI> list = CrawlerUtils.getFileUriLinks(doc);
			retrievedDocs.add(new RetrievedDocument(u, doc.text(), list));
			
			if(list.size() > 0) {
				for(int i = 0; i < list.size(); i++) {
					if(!newStack.contains(list.get(i))) {
						newStack.push(list.get(i));
						uri.add(list.get(i));
						stack.push(list.get(i));
						visitedUri.add(list.get(i));
					}
				}
			}
	
		}
		
		visitQuota--;		
	}

	/**
	 * Attempts to visit all URIs in this crawler (and any URIs they reference,
	 * and so on).
	 * 
	 * This method will not raise a MaximumVisitsExceededException if there are
	 * more URIs than can be visited. It will instead stop once the UriCrawler's
	 * quota has been reached.
	 */
	public void visitAll() {
		// TODO
		for(int i=0; i < visitQuota; i++) {			
			try {
				visitOne();
			} catch (MaximumVisitsExceededException | NoUnvisitedUrisException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	private boolean noUnvisited() {
		Iterator<URI> it = uri.iterator();
		
		if(uri != null || uri.size()>0) {
			while(it.hasNext()) {
				URI u = it.next();
				
				if(!newStack.contains(u)) {
					newStack.push(u);
				}
			}
		}
		
		if(newStack.size() == 0) {
			return true;
		}
		
		return false;
	}
}
