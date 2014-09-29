/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Adaptado por Ricardo Chávez para el diplomado de Desarrollo de Aplicaciones Móviles de la 
 * Universidad Ricardo Palma
 */

package com.diplomadourp.ejemploconexionxml;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase parsea feeds XML de stackoverflow.com.
 * Recibe un InputStream que representa el feed y devuelve un List de entradas,
 * en el cual cada elemento representa una entrada (post) en el feed XML.
 */
public class StackOverflowXmlParser {
    private static final String ns = null;
    
    private String titulo;

    public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

    // No utilizamos namespaces

	public List<Entry> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private List<Entry> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Entry> entries = new ArrayList<Entry>();

        // Comenzamos buscando el tag <feed>
        parser.require(XmlPullParser.START_TAG, ns, "feed");
        while (parser.next() != XmlPullParser.END_TAG) {
        	// Ignoramos los eventos recibidos que no nos indiquen el inicio de un tag
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Si encontramos un tag <entry>, lo procesamos
            if (name.equals("entry")) {
                entries.add(readEntry(parser));
            } else if (name.equals("title")) {
            	setTitulo(readTitle(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    // Procesa el contenido del tag <entry>. Si encuentra los tags <title>, <summary> o <link>,
    // llama a los métodos correspondientes para obtener la información; cualquier otro tag es
    // ignorado.
    private Entry readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "entry");
        Entry post = new Entry();
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("title")) {
            	post.setTitle(readTitle(parser));
            } else if (name.equals("summary")) {
                post.setSummary(readSummary(parser));
            } else if (name.equals("link")) {
                post.setLink(readLink(parser));
            } else if (name.equals("category")) {
            	String tag = readCategory(parser);
            	if (post.getTags() == null || post.getTags().length() == 0) {
            		post.setTags(tag);
            	}
            	else {
            		post.setTags(post.getTags() + ", " + tag);
            	}
            } else {
                skip(parser);
            }
        }
        return post;
    }

	// Procesa los tags <category>
    private String readCategory(XmlPullParser parser) throws XmlPullParserException, IOException {
    	String category = "";
    	parser.require(XmlPullParser.START_TAG, ns, "category");
    	String tag = parser.getName();
    	String scheme = parser.getAttributeValue(null, "scheme");
    	if (tag.equals("category")) {
			if (scheme.equals("http://stackoverflow.com/tags")) {
				category = parser.getAttributeValue(null, "term");
				parser.nextTag();
			}
		}
    	parser.require(XmlPullParser.END_TAG, ns, "category");
		return category;
	}

	// Procesa los tags <title>
    private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "title");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "title");
        return title;
    }

    // Procesa los tags <link>
    private String readLink(XmlPullParser parser) throws IOException, XmlPullParserException {
        String link = "";
        parser.require(XmlPullParser.START_TAG, ns, "link");
        String tag = parser.getName();
        String relType = parser.getAttributeValue(null, "rel");
        if (tag.equals("link")) {
            if (relType.equals("alternate")) {
                link = parser.getAttributeValue(null, "href");
                parser.nextTag();
            }
        }
        parser.require(XmlPullParser.END_TAG, ns, "link");
        return link;
    }

    // Procesa los tags <summary>
    private String readSummary(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "summary");
        String summary = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "summary");
        return summary;
    }

    // Extrae el contenido de texto de dentro de un tag.
    // Se utiliza para los tags <title> y <summary>
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    // Ignora los tags que no contienen información de interés. Utiliza profundidad para
    // manejar el caso de tags anidados: si el siguiente tag después de un tag de apertura
    // no es el tag de cierre correspondiente, sigue avanzando hasta hallar el tag de cierre
    // correspondiente (lo cual se determina verificando que el valor de 'depth' sea 0)
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
            case XmlPullParser.END_TAG:
                    depth--;
                    break;
            case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
