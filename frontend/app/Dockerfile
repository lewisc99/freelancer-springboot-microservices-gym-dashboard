# based on https://mherman.org/blog/dockerizing-an-angular-app/
# base image
FROM node:18.12.1 as node
WORKDIR /app
COPY . .
RUN apt-get update && apt-get upgrade -y && \
    apt-get install -y nodejs \
    npm   

RUN npm install
RUN npm run build --prod
# Stage 2
FROM nginx:alpine
COPY --from=node /app/dist/app /usr/share/nginx/html