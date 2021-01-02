const express = require('express'),
{ GraphQLSchema, GraphQLObjectType, GraphQLString, GraphQLInt, GraphQLList} = require('graphql'),
{ graphqlHTTP } = require('express-graphql'),
app = express();

const { URLSearchParams } = require('url');
global.URLSearchParams = URLSearchParams;

const todos = [
  {id:1, whatToDo: 'Eat egg'},
  {id:2, whatToDo: 'Sing a song'},
  {id:3, whatToDo: 'Call my friends'}
]

const ToDoType = new GraphQLObjectType({
  name: 'Todo',
  description: 'list of things yo need to do',
  fields: {
    id: {type: GraphQLInt},
    whatToDo: {type: GraphQLString}
  }
}) 

const RootQuery = new GraphQLObjectType({
  name: 'Query',
  description: 'this is the root query',
  fields:{
    todos: { 
      type: GraphQLList(ToDoType),
      resolve: ()=> todos 
    },
  }
})

var schema = new GraphQLSchema({query: RootQuery});

app.use(
  '/graphql', graphqlHTTP({
    graphiql: true,
    schema
  }),
);

const PORT = 3000
app.listen(PORT, ()=> {
  console.log(`listening on post ${PORT}`)
})