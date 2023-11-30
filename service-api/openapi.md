---
title: OpenApi 注解
date: 2023-11-30 10:16:11
---

## @OpenAPIDefinition
作用： 定义有关API的基本信息，如标题、版本等。
可用元素：
- info：包含API信息的注解，如标题、版本等。
- openapi：指定所使用的OpenAPI规范的版本。
- servers：描述API的服务器，包括URL和其他详细信息。
- externalDocs：提供对外部文档的引用，以获取额外信息。
- tags：描述API的标签，包括名称、描述和其他元信息。
- security：定义全局的安全要求，应用于所有操作。
## @Path
作用： 指定资源或类的基本URI路径。
可用元素：
- value：路径的字符串值，用于指定URI路径。
- name：路径的名称。
- description：路径的描述信息。
## @Operation
作用： 描述资源上的操作（HTTP方法），包括摘要和描述。
可用元素：
- summary：操作的摘要。
- description：操作的描述。
- operationId：操作的唯一标识符。
- deprecated：指示操作是否已过时。
- parameters：操作的参数。
## @RequestBody
作用： 定义操作的请求体，包括其内容类型。
可用元素：
- content：请求体的内容类型。
- description：请求体的描述。
- required：指示请求体是否为必需。
## @APIResponse
作用： 描述操作的可能响应，包括HTTP状态码、描述和内容。
可用元素：
- responseCode：HTTP状态码。
- description：响应的描述。
- content：响应的内容类型。
- headers：响应的标头。
## @Content
作用： 指定在@APIResponse或@RequestBody中的内容的媒体类型和模式。
可用元素：
- mediaType：内容的媒体类型。
- schema：内容的模式。
## @Parameter
作用： 描述操作的参数，包括名称、类型和位置（路径、查询、标头等）。
可用元素：
- name：参数的名称。
- in：参数的位置（path、query、header等）。
- description：参数的描述。
- required：指示参数是否为必需。
- schema：参数的模式。
## @ApiResponse
作用： 表示OpenAPI规范中的可能响应，包括HTTP状态码、描述和内容。
可用元素：
- responseCode：HTTP状态码。
- description：响应的描述。
- content：响应的内容类型。
- headers：响应的标头。
## @Schema
作用： 定义模型、参数或响应的数据类型和结构。
可用元素：
- type：数据类型。
- format：数据格式。
- description：数据的描述。
- example：数据的示例值。
## @Tag
作用： 提供有关在OpenAPI文档中使用的标签的元信息，如名称和描述。
可用元素：
- name：标签的名称。
- description：标签的描述。
- externalDocs：提供对外部文档的引用，以获取额外信息。
## @Server
作用： 描述托管API的服务器，包括其URL和其他详细信息。
可用元素：
- url：服务器的URL。
- description：服务器的描述。
- variables：服务器URL中的变量。
## @ExternalDocumentation
作用： 提供对外部文档的引用，以获取额外信息。
可用元素：
- url：外部文档的URL。
- description：文档的描述。



