import { Rule, SchematicContext, Tree, apply, template, url, mergeWith, SchematicsException, move } from '@angular-devkit/schematics';
import { Schema } from './schema';
import { strings } from '@angular-devkit/core';
import { buildDefaultPath } from '@schematics/angular/utility/project';
import { parseName } from '@schematics/angular/utility/parse-name';

// You don't have to export the function as default. You can also have more than one rule factory
// per file.
export function hello(_options: Schema): Rule {
  return (tree: Tree, _context: SchematicContext) => {
    /* 
    1-this is the first work 

    const { name } = _options;
    tree.create('hello.js', `console.log('Hello ${name} !')`);
    return tree; 
    */


    // 3- this is the third work
    const workspaceConfigBuffer = tree.read("angular.json"); // read angular.json file as buffer and verify if we are in Angular CLI workspace
    if (!workspaceConfigBuffer) {
      throw new SchematicsException("Not an Angular CLI workspace");
    }
    const workspaceConfig = JSON.parse(workspaceConfigBuffer.toString()); // parse Config
    const projectName = _options.project || workspaceConfig.defaultProject; // we are going to use the project attribute, else we are going to read the value from angular.json file
    const project = workspaceConfig.projects[projectName]; // get the project definition which is a json object.

    // first, buildDefaultPath(project) gets us default path for project
    // e.g: src/app
    const defaultProjectPath = buildDefaultPath(project);

    // Second, _options.name can contain path: e.g: some-feature/some-sub-feature/some-service
    // The parseName() will separate that into path: some-feature/some-sub-feature and some-service
    // The default project path will be prepended to the passed path.
    // That way we end up with path: src/app/some-feature/some-sub-feature
    const parsedPath = parseName(defaultProjectPath, _options.name);

    const { name, path } = parsedPath;

    // 2- here is the second work
    const sourceTemplates = url('./files'); // get hold of our templates folders & files
    const sourceParametrizedTemplates = apply(sourceTemplates, [  // parametrize our template sources; apply accepts source and array of rules
      template({                                                  
        ..._options,
        ...strings,
        addExclamation,
        name                                                      // pass in name (to overwrite _options.name value)     
      }),
      move(path)                                                  // move file to resolved path
    ]);
    return mergeWith(sourceParametrizedTemplates)(tree, _context); // we merge our template into the tree

    // mergeWith() returns a rule so it can be called with tree and _context but thats not necessary. the code is commented below: 
    // return mergeWith(sourceParametrizedTemplates)(tree, _context)
  };
}

function addExclamation(value: string): string {
  return value + '!';
}
