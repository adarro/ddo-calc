import { parse, convert, Options } from 'odata2openapi';

const options: Options = {
  host: 'localhost:8080',
  path: '/'
};

// Get the OData metadata as a string.
const xml = '';

parse(xml)
  .then(entitySets => convert(entitySets, options))
  .then(swagger => console.log(JSON.stringify(swagger, null, 2)))
  .catch(error => console.error(error))