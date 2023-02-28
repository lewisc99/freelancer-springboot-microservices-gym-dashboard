# based on https://mherman.org/blog/dockerizing-an-angular-app/
# base image
FROM node:18.12.1 as node
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod
# Stage 2
FROM nginx:alpine
COPY --from=node /app/dist/angular-app /usr/share/nginx/html