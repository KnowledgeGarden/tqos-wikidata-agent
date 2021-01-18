--
-- Create a schema to hide the  tables from public view.
-- This builds on the established roles from the tq schema
--
CREATE SCHEMA IF NOT EXISTS tq_wikidata;
GRANT ALL ON schema tq_wikidata TO tq_proxy;
GRANT USAGE ON schema tq_wikidata TO tq_proxy_ro;

CREATE TABLE IF NOT EXISTS 
tq_wikidata.items (
	id text NOT NULL PRIMARY KEY,
	label text NOT NULL,
	data text
);

GRANT ALL PRIVILEGES ON tq_wikidata.items TO tq_proxy;
GRANT SELECT ON tq_wikidata.items TO tq_proxy_ro;

CREATE INDEX IF NOT EXISTS idx_id ON tq_wikidata.items(id);

CREATE INDEX IF NOT EXISTS idx_label ON tq_wikidata.items(label);
